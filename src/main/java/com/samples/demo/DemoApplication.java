package com.samples.demo;

import com.samples.demo.annotation.MyAnnotation;
import com.samples.demo.annotation.MyClass;
import com.samples.demo.annotation.custom.JsonSerializer;
import com.samples.demo.annotation.custom.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		/**
		 * Sample to read values passed in Annotation
		 *
		Class classElement = MyClass.class;
		Parameter rankParam = null;
		try{
			Class[] classes = new Class[1];
			classes[0] = Integer.class;
			Method setRankMethod = classElement.getMethod("setRank", classes);
			rankParam = setRankMethod.getParameters()[0];

			MyAnnotation annotation = rankParam.getAnnotation(MyAnnotation.class);
			System.out.println(annotation.level()+ " - "+annotation.name());
		}catch (Exception e){
			e.printStackTrace();
		}*/

		Person person = new Person("soufiane", "cheouati", "34");
		JsonSerializer serializer = new JsonSerializer();
		String jsonString = serializer.serialize(person);
		System.out.println(jsonString);

		Map pets = new HashMap();
		pets.put("animal","dog");
		pets.put("bird", "parrot");
		Person person2 = new Person("John", "Doe", "22", pets);
		System.out.println(serializer.serialize(person2));
		//assertEquals("{\"personAge\":\"34\",\"firstName\":\"Soufiane\",\"lastName\":\"Cheouati\"}", jsonString);
	}

}
