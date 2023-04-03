package hkmu.comps380f.dao;

import hkmu.comps380f.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PhotoPosterRepository extends JpaRepository<AppUser, String>{
}
