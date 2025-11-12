package space;

import space.domain.Space;

import java.util.List;
import java.util.ArrayList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 공간 정보를 메모리에 저장하고 관리하는 저장소
 */
public class SpaceRepository {
    private static final Map<Long, Space> store = new ConcurrentHashMap<>();
    // private static final AtomicLong seq = new AtomicLong(1L);

    public Long save(Space space) {
        Long id = space.getId();
        store.put(id, space);
        return id;
    }
    public Optional<Space> findById(Long id) { return Optional.of(store.get(id)); }

    public List<Space> findAll() { return new ArrayList<>(store.values()); }

    public boolean deleteById(Long id) { if(findById(id).isPresent()) {
        store.remove(id);
        return true;
    }else return false;}

    public boolean updateById(Long id, Space updateSpace) {
        if(findById(id).isPresent()) {
            store.replace(id, updateSpace);
            return true;
        }else return false;
    }
}
