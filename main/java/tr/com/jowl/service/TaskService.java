package tr.com.jowl.service;

import java.util.List;

import tr.com.jowl.entity.Task;

/**
 * The TaskService interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0 Date 4/27/2018.
 */
public interface TaskService {

	Task save(Task task);

	Boolean delete(int id);

	Task update(Task task);

	Task findById(int id);

	List<Task> findAll();

	List<Task> findByStatus(String status);

	List<Task> findByUserIdStatus(int userId, String status);

	List<Task> findBetween(int start, int end);

}
