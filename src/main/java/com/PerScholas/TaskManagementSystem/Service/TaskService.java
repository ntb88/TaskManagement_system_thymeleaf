package com.PerScholas.TaskManagementSystem.Service;

import com.PerScholas.TaskManagementSystem.Model.Task;
import com.PerScholas.TaskManagementSystem.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){

        return taskRepository.findAll();
    }


    public Optional<Task> getTaskById(Long id){

        return taskRepository.findById(id);
    }

    public Task createTask(Task task){

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task newTask){
        return taskRepository.findById(id)
                .map(oldTask -> {
                    oldTask.setTitle(newTask.getTitle());
                    oldTask.setDescription(newTask.getDescription());
                    oldTask.setStatus(newTask.getStatus());
            return taskRepository.save(oldTask); //save the old task with new values
        }).orElseThrow(()-> new RuntimeException("Task Not Found!"));
    }


    public void deleteTask(Long id){

        taskRepository.deleteById(id);
    }


}
