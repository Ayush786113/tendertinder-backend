package com.tendertinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedList;

public class Recommendations implements Runnable, Callback {
    private Appwrite appwrite;

    private String token;

    Recommendations(String token){
        appwrite = new Appwrite();
        this.token = token;
    }

    synchronized public static void main(String[] args) {
//        while (true){
//            try {
//                new Recommendations("633598e5-f7d6-479e-8891-7fcd56e0e622").getRecommendations("633598e5-f7d6-479e-8891-7fcd56e0e622");
//            } catch (Exception exception){
//                continue;
//            }
//        }
    }

    @Override
    synchronized public void run() {
        try{
            int i = 25;
            while(i-- >= 0) {
                getRecommendations(this.token);
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    synchronized public void getRecommendations(String token) throws Exception{
        HttpResponse<String> response = null;
        LinkedList<Person> people = new LinkedList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.gotinder.com/v2/recs/core"))
                .header("X-Auth-Token", token)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        if(response.statusCode() != 200) {
            System.out.println(response.body());
            System.exit(1);
        }
        ObjectMapper mapper = new ObjectMapper();
        RecsResponse recsResponse = mapper.readValue(response.body(), RecsResponse.class);
//            try {
//                RecsResponse recsResponse = mapper.readValue(response.body(), RecsResponse.class);
//            } catch (UnrecognizedPropertyException unrecognizedPropertyException){
//                System.out.println(response.body());
//                System.out.println(unrecognizedPropertyException.getMessage()+"\n");
//                System.exit(1);
//            }
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
            appwrite.writeRecommendation(this, person);
            wait();
        }
    }

    @Override
    synchronized public void done() {
        notify();
    }
}
