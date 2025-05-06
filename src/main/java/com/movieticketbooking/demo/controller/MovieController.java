package com.movieticketbooking.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.movieticketbooking.demo.model.Movie;
import com.movieticketbooking.demo.model.User;
import com.movieticketbooking.demo.service.MovieService;
import com.movieticketbooking.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movie_details";
    }

    @PostMapping("/book/{id}")
    public String bookTickets(@PathVariable Long id, 
                          @RequestParam String name, 
                          @RequestParam String email,
                          @RequestParam String phone, 
                          @RequestParam int seats, 
                          Model model) {
    Movie movie = movieService.getMovieById(id);
    
    // Check if the requested seats exceed available seats
    if (seats > movie.getAvailableSeats()) {
        model.addAttribute("error", "Ticket limit exceeded! Available seats: " + movie.getAvailableSeats());
        model.addAttribute("movie", movie);
        return "movie_details"; // Show the movie details page with an error
    }
    
    // Proceed to book tickets if the limit is not exceeded
    movie.bookSeats(seats);
    
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPhone(phone);
    user.setBookedSeats(seats);
    user.setTotalAmount(seats * movie.getTicketPrice());
    
    userService.saveUser(user);
    movieService.saveMovie(movie);
    
    return "redirect:/movies"; // Redirect after booking
}

}
