package com.cisco.bpm.api;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks/{assignee}")
    public List<Task> getTasks(@PathVariable String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @PostMapping("/complete/{taskId}")
    public String completeTask(@PathVariable String taskId) {
        taskService.complete(taskId);
        return "Task with ID " + taskId + " has been completed!";
    }

}
