package com.tendertinder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

class AddPrompt{
    public String text;
    public TappedAction tapped_action;
}

class Album{
    public String id;
    public String name;
    public ArrayList<Image> images;
}

class Algo{
    public double width_pct;
    public double x_offset_pct;
    public double height_pct;
    public double y_offset_pct;
}

class AnswerDetail{
    public String emoji;
    public String prompt_text;
    public String answer_id;
    public String answer_text;
}

class Artist{
    public String id;
    public String name;
}

class Asset{
    public String url;
    public String asset_type;
    public int width;
    public int height;
    public ArrayList<String> enhancements;
}

class Badge{
    public String type;
}

class ChoiceSelection{
    public String id;
    public String name;
}

class City{
    public String name;
}

class Company{
    public String name;
}

class Content{
    public String id;
    public String type;
}

class CropInfo{
    public User user;
    public Algo algo;
    public boolean processed_by_bullseye;
    public boolean user_customized;
    public ArrayList<Face> faces;
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Data{
    public ArrayList<Result> results;
}

class Distance{
    public TextV1 text_v1;
}

class ExperimentInfo{
    public UserInterests user_interests;
}

class Face{
    public Algo algo;
    public double bounding_box_percentage;
}

class Facebook{
    public ArrayList<Object> common_connections;
    public int connection_count;
    public ArrayList<Object> common_interests;
}

class HiddenIntent{
    public String emoji;
    public String image_url;
    public String title_text;
    public String body_text;
}

class Icon{
    public String local_asset;
}

class IconUrl{
    public String url;
    public String quality;
    public int width;
    public int height;
}

class IdToComponentMap{
    public Distance distance;
}

class Image{
    public int height;
    public int width;
    public String url;
}

class Instagram{
    public String last_fetch_time;
    public boolean completed_initial_fetch;
    public ArrayList<Photo> photos;
    public int media_count;
}

class Job{
    public Title title;
    public Company company;
}

class MeasurableSelection{
    public int value;
    public int min;
    public int max;
    public String unit_of_measure;
}

class Meta{
    public int status;
}

class Mutuals{
    public int mutuals_count;
    public boolean user_opt_in;
    public boolean rec_opt_in;
    public ArrayList<Object> mutuals;
    public MysteryMutuals mystery_mutuals;
}

class MysteryMutuals{
    public int count;
}

class Photo{
    public String id;
    public CropInfo crop_info;
    public String url;
    public ArrayList<ProcessedFile> processedFiles;
    public ArrayList<ProcessedVideo> processedVideos;
    public String fileName;
    public String extension;
    public ArrayList<Asset> assets;
    public String media_type;
    public String image;
    public String thumbnail;
    public String ts;
}

class ProcessedFile{
    public String url;
    public int height;
    public int width;
}

class ProcessedVideo{
    public String url;
    public int height;
    public int width;
}

class ProfileDetailContent{
    public ArrayList<Object> content;
    public String page_content_id;
}

class Prompt{
    public String id;
    public String question_text;
    public String answer_id;
    public String answer_text;
    public String image_url;
}

class QueryParams{
    public String component_id;
    public String type;
}

class Quiz{
    public String id;
    public String name;
    public ArrayList<String> answers;
    public ArrayList<AnswerDetail> answer_details;
    public String image_url;
    public String locked_image_url;
}

class RelationshipIntent{
    public String descriptor_choice_id;
    public String emoji;
    public String image_url;
    public String title_text;
    public String body_text;
    public String style;
    public HiddenIntent hidden_intent;
    public TappedAction tapped_action;
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Result{
    public String type;
    public User user;
    public Facebook facebook;
    public Spotify spotify;
    public int distance_mi;
    public String content_hash;
    public Object s_number;
    public Teaser teaser;
    public ArrayList<Teaser> teasers;
    public ExperimentInfo experiment_info;
    public boolean is_superlike_upsell;
    public ArrayList<TappyContent> tappy_content;
    public ArrayList<ProfileDetailContent> profile_detail_content;
    public UiConfiguration ui_configuration;
    public ArrayList<Object> user_posts;
    public Mutuals mutuals;
    public Instagram instagram;
}
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecsResponse{
    public Meta meta;
    public Data data;
}

class School{
    public String name;
}

class SelectedDescriptor{
    public String id;
    public String name;
    public String prompt;
    public String type;
    public String icon_url;
    public ArrayList<IconUrl> icon_urls;
    public ArrayList<ChoiceSelection> choice_selections;
    public String section_id;
    public String section_name;
    public MeasurableSelection measurable_selection;
}

class SelectedInterest{
    public String id;
    public String name;
    public boolean is_common;
}

class SexualOrientation{
    public String id;
    public String name;
}

class SparksQuiz{
    public ArrayList<Quiz> quizzes;
    public String section_id;
    public String section_name;
    public String locked_button_text;
}

class Spotify{
    public boolean spotify_connected;
    public ArrayList<Object> spotify_top_artists;
    public SpotifyThemeTrack spotify_theme_track;
}

class SpotifyThemeTrack{
    public String id;
    public String name;
    public Album album;
    public ArrayList<Artist> artists;
    public String preview_url;
    public String uri;
}

class TappedAction{
    public String method;
    public String url;
    public QueryParams query_params;
}

class TappyContent{
    public ArrayList<Content> content;
}

class Teaser{
    public String type;
    public String string;
}

class Teaser2{
    public String type;
    public String string;
}

class TextV1{
    public String content;
    public Icon icon;
    public String style;
}

class Title{
    public String name;
}

class UiConfiguration{
    public IdToComponentMap id_to_component_map;
}

class User{
    public String _id;
    public ArrayList<Badge> badges;
    public String bio;
    public String birth_date;
    public String name;
    public ArrayList<Photo> photos;
    public int gender;
    public ArrayList<Job> jobs;
    public ArrayList<School> schools;
    public boolean show_gender_on_profile;
    public boolean online_now;
    public ArrayList<SelectedDescriptor> selected_descriptors;
    public RelationshipIntent relationship_intent;
    public City city;
    public String custom_gender;
    public ArrayList<SexualOrientation> sexual_orientations;
    public ArrayList<SparksQuiz> sparks_quizzes;
    public boolean is_traveling;
    public UserPrompts user_prompts;
    public boolean bumper_sticker_enabled;
    public boolean hide_age;
    public boolean hide_distance;
    public double width_pct;
    public double x_offset_pct;
    public double height_pct;
    public double y_offset_pct;
}

class UserInterests{
    public ArrayList<SelectedInterest> selected_interests;
}

class UserPrompts{
    public String section_name;
    public ArrayList<Prompt> prompts;
    public AddPrompt add_prompt;
}

