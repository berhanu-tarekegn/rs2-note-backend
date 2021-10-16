package com.rs2.note.note;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CustomNoteRepositoryImpl implements CustomNoteRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Note> search(String filter) {

        Query query = entityManager.createNativeQuery("SELECT * FROM note n JOIN note_label nl ON nl.note_id = n.id JOIN label l ON l.id = nl.label_id WHERE lower(n.title) like lower(?1) OR lower(n.content) like lower(?1) OR lower(l.name) like lower(?1)", Note.class);

        query.setParameter(1, "%"+filter+"%");

        try {
            return query.getResultList();
        } catch (NoResultException ignored) {
            return new ArrayList<>();
        }

    }
}
