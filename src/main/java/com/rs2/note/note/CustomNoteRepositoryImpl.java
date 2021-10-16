package com.rs2.note.note;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class CustomNoteRepositoryImpl implements CustomNoteRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Note> search(String filter) {

        Query query = entityManager.createNativeQuery("SELECT  * FROM note n WHERE note.title LIKE %?% OR note.content LIKE %?% ORDER BY name ASC");

        query.setParameter(1, filter);

        try {
            return query.unwrap( org.hibernate.query.NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(Note.class)).getResultList();
        } catch (NoResultException ignored) {
            return null;
        }

    }
}
