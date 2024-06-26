package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor // == @Autowired private TodoRepository todoRepository;
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> getList() {
        // 미완료 목록
        List<Todo> list = todoRepository.findByCompleted(false);

        // Todo => TodoDto 변환
        // List<TodoDto> todoList = new ArrayList<>();
        // list.forEach(todo -> todoList.add(entityToDto(todo)));

        List<TodoDto> todoList = list.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());

        return todoList;
    }

    @Override
    public TodoDto create(TodoDto dto) {
        // TodoDto => Todo 변환
        Todo entity = todoRepository.save(dtoToEntity(dto));

        return entityToDto(entity);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).get();
        return entityToDto(todo);
    }

    @Override
    public List<TodoDto> getCompletedList() {
        List<Todo> result = todoRepository.findByCompleted(true);

        // Todo => TodoDto 변환
        // List<TodoDto> compList = new ArrayList<>();
        // result.forEach(todo -> compList.add(entityToDto(todo)));

        List<TodoDto> compList = result.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());

        return compList;
    }

    @Override
    public Long todoupdate(Long id) {
        // 업데이트 완료 후 id 만 리턴
        Todo entity = todoRepository.findById(id).get();
        entity.setCompleted(true);
        Todo todo = todoRepository.save(entity);
        return todo.getId();
    }

    @Override
    public void todoDelete(Long id) {

        todoRepository.deleteById(id);
    }

}
