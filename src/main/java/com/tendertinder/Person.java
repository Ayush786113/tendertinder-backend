package com.tendertinder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    String id, name, birth_date, bio, unit_of_measure, city, relationship_intent;
    ArrayList<String> photos, interests;
    Integer gender, height;
}
