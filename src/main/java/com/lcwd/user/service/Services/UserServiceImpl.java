package com.lcwd.user.service.Services;

import com.lcwd.user.service.Entities.Hotel;
import com.lcwd.user.service.Entities.Rating;
import com.lcwd.user.service.Entities.User;
import com.lcwd.user.service.Exception.ResourceNotFoundException;
import com.lcwd.user.service.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
//        User user= (User) userRepo.findAll();
//        ArrayList a = restTemplate.getForObject("http://localhost:8083/ratings/users", ArrayList.class);
//        user.setRatings(a);
//        return (List<User>) user;
    }

    @Override
    public User getUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server"));
        Rating[] userRating = restTemplate.getForObject("http://RatingService/ratings/users/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(userRating).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
          ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HotelService/hotels/"+rating.getHotelId(), Hotel.class);
          Hotel hotel=forEntity.getBody();
            rating.setHotel(hotel);
            return new Rating();
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
