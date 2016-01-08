package thtesche.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author thtesche
 */
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  
}
