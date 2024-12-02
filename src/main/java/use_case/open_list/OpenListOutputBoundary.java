package use_case.open_list;

import entity.Movie;

import java.util.List;

/**
 * The output boundary for the OpenList Use Case.
 */
public interface OpenListOutputBoundary {

    void prepareSuccessView(OpenListOutputData outputData);

    void prepareFailView(String error);

}
