package org.formation.service;

import java.util.List;

import org.formation.model.Movie;

public interface MovieLister {

	public List<Movie> moviesDirectedBy(String director);
}
