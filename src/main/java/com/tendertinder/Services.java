package com.tendertinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

@Service
public class Services implements ServiceCallback{
    @Autowired
    Appwrite appwrite;
    private Object data;
    synchronized public Object getProfiles() throws Exception{
        appwrite.getProfiles(this);
        wait();
        return data;
    }
    synchronized public Object getRecommendations(String token) throws Exception{
        Thread thread = new Thread(new Recommendations(token));
        thread.start();
        HttpResponse<String> response = null;
        LinkedList<Person> people = new LinkedList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.gotinder.com/v2/recs/core"))
                .header("X-Auth-Token", token)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200)
            throw new Exception("Status code other than 200!");

        ObjectMapper mapper = new ObjectMapper();
        RecsResponse recsResponse = mapper.readValue(response.body(), RecsResponse.class);
        for(Result result : recsResponse.data.results){
            Person person = new Person();
            person.setId(result.user._id.trim());
            person.setName(result.user.name.trim());
            person.setGender(result.user.gender);
            person.setBio(result.user.bio.trim());
            person.setBirth_date(result.user.birth_date.trim());

            if(result.user.city != null)
                person.setCity(result.user.city.name.trim());

            ArrayList<String> photos = new ArrayList<>();
            for(Photo photo : result.user.photos) {
                photos.add(photo.url.trim());
            }
            person.setPhotos(photos);

            if(result.user.relationship_intent != null)
                person.setRelationship_intent(result.user.relationship_intent.body_text.trim());

            if(result.experiment_info != null) {
                ArrayList<String> selectedInterests = new ArrayList<>();
                for (SelectedInterest selectedInterest : result.experiment_info.user_interests.selected_interests) {
                    selectedInterests.add(selectedInterest.name.trim());
                }
                person.setInterests(selectedInterests);
            }

            if(result.user.selected_descriptors != null) {
                for (SelectedDescriptor selectedDescriptor : result.user.selected_descriptors) {
                    if (selectedDescriptor.section_name.equals("Height")) {
                        person.setHeight(selectedDescriptor.measurable_selection.value);
                        person.setUnit_of_measure(selectedDescriptor.measurable_selection.unit_of_measure.trim());
                        break;
                    }
                }
            }
            people.add(person);
        }
        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                return person.id.compareTo(t1.id);
            }
        });
        return people;
    }
    synchronized public Object myLikes(String token) throws Exception{
        Thread thread = new Thread(new Recommendations(token));
        thread.start();
        HttpResponse<String> response = null;
        LinkedList<Person> people = new LinkedList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.gotinder.com/v2/my-likes"))
                .header("X-Auth-Token", token)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200)
            throw new Exception("Status code other than 200!");

        ObjectMapper mapper = new ObjectMapper();
        RecsResponse recsResponse = mapper.readValue(response.body(), RecsResponse.class);
        for(Result result : recsResponse.data.results){
            Person person = new Person();
            person.setId(result.user._id.trim());
            person.setName(result.user.name.trim());
            person.setGender(result.user.gender);
            person.setBio(result.user.bio.trim());
            person.setBirth_date(result.user.birth_date.trim());

            if(result.user.city != null)
                person.setCity(result.user.city.name.trim());

            ArrayList<String> photos = new ArrayList<>();
            for(Photo photo : result.user.photos) {
                photos.add(photo.url.trim());
            }
            person.setPhotos(photos);

            if(result.user.relationship_intent != null)
                person.setRelationship_intent(result.user.relationship_intent.body_text.trim());

            if(result.experiment_info != null) {
                ArrayList<String> selectedInterests = new ArrayList<>();
                for (SelectedInterest selectedInterest : result.experiment_info.user_interests.selected_interests) {
                    selectedInterests.add(selectedInterest.name.trim());
                }
                person.setInterests(selectedInterests);
            }

            if(result.user.selected_descriptors != null) {
                for (SelectedDescriptor selectedDescriptor : result.user.selected_descriptors) {
                    if (selectedDescriptor.section_name.equals("Height")) {
                        person.setHeight(selectedDescriptor.measurable_selection.value);
                        person.setUnit_of_measure(selectedDescriptor.measurable_selection.unit_of_measure.trim());
                        break;
                    }
                }
            }
            people.add(person);
        }
        return people;
    }

    synchronized public Object searchByName(String name) throws Exception{
        appwrite.searchByName(this, name);
        wait();
        return data;
    }
    synchronized public Object searchByGender(String gender) throws Exception{
        if(gender.equalsIgnoreCase("male"))
            appwrite.searchByGender(this, 0);
        else if(gender.equalsIgnoreCase("female"))
            appwrite.searchByGender(this, 1);
        wait();
        return data;
    }
    synchronized public Object searchByCity(String city) throws Exception{
        appwrite.searchByCity(this,city);
        wait();
        return data;
    }

    @Override
    synchronized public void result(Object data) {
        this.data = data;
    }

    @Override
    synchronized public void done() {
        notify();
    }
}
