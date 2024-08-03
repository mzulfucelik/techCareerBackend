package com.techcareer.todolist.config;

import com.techcareer.todolist.service.tasks.TaskMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean
    public TaskMapper taskMapper(){
        return new TaskMapper();
    }
}
