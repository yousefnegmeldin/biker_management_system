package com.brightskies.biker_system.store.service;
import com.brightskies.biker_system.exception.model.StoreNotFoundException;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService
{
    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long id)
    {
        Store store = storeRepository.findById(id)
                        .orElseThrow(() -> new StoreNotFoundException(id));
        storeRepository.deleteById(id);
    }

}
