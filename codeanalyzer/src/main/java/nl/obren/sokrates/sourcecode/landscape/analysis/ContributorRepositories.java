package nl.obren.sokrates.sourcecode.landscape.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.obren.sokrates.sourcecode.contributors.Contributor;

import java.util.ArrayList;
import java.util.List;

public class ContributorRepositories {
    private Contributor contributor;
    private List<ContributorRepositoryInfo> repositories = new ArrayList<>();

    public ContributorRepositories(Contributor contributor) {
        this.contributor = contributor;
    }

    public void addRepository(RepositoryAnalysisResults repositoryAnalysisResults, RepositoryInfo repositoryInfo) {
        String path = repositoryAnalysisResults.getAnalysisResults().getMetadata().getName();
        ContributorRepositoryInfo repositoryByPath = getRepositoryByPath(path);
        if (repositoryByPath != null) {
            if (repositoryInfo.getFirstCommitDate().compareTo(repositoryByPath.getFirstCommitDate()) < 0) {
                repositoryByPath.setFirstCommitDate(repositoryInfo.getFirstCommitDate());
            }
            if (repositoryInfo.getLatestCommitDate().compareTo(repositoryByPath.getLatestCommitDate()) > 0) {
                repositoryByPath.setLatestCommitDate(repositoryInfo.getLatestCommitDate());
            }
            repositoryByPath.setCommits30Days(repositoryByPath.getCommits30Days() + repositoryInfo.getCommits30Days());
            repositoryByPath.setCommits90Days(repositoryByPath.getCommits90Days() + repositoryInfo.getCommits90Days());
            repositoryByPath.setCommitsCount(repositoryByPath.getCommitsCount() + repositoryInfo.getCommitsCount());
            repositoryInfo.getCommitDates().forEach(date -> {
                if (!repositoryByPath.getCommitDates().contains(date)) {
                    repositoryByPath.getCommitDates().add(date);
                }
            });
        } else {
            repositories.add(new ContributorRepositoryInfo(repositoryAnalysisResults, repositoryInfo.getFirstCommitDate(), repositoryInfo.getLatestCommitDate(),
                    repositoryInfo.getCommitsCount(), repositoryInfo.getCommits30Days(), repositoryInfo.getCommits90Days(), repositoryInfo.getCommitDates()));
        }
    }


    public void addRepository(ContributorRepositoryInfo repository) {
        repositories.add(repository);
    }

    @JsonIgnore
    private ContributorRepositoryInfo getRepositoryByPath(String path) {
        for (ContributorRepositoryInfo repository : repositories) {
            if (repository.getRepositoryAnalysisResults().getAnalysisResults().getMetadata().getName().equalsIgnoreCase(path)) {
                return repository;
            }
        }
        return null;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public List<ContributorRepositoryInfo> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<ContributorRepositoryInfo> repositories) {
        this.repositories = repositories;
    }
}
