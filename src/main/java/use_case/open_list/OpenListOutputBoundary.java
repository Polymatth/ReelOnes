package use_case.open_list;

import entity.Movie;

import java.util.List;

public interface OpenListOutputBoundary {

    void prepareSuccessView(OpenListOutputData outputData);

    void prepareFailView(String error);

}
