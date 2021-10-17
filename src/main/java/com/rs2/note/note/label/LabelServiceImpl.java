package com.rs2.note.note.label;

import com.rs2.note.common.exceptions.ItemAlreadyExistsException;
import com.rs2.note.common.exceptions.ItemNotFoundException;
import com.rs2.note.note.Note;
import com.rs2.note.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {

    private static final Logger log = LoggerFactory.getLogger(LabelServiceImpl.class);

    private final LabelRepository labelRepository;

    public LabelServiceImpl(final LabelRepository labelRepository ) {
        this.labelRepository = labelRepository;
    }

    public Label findLabelByName(String name) {

        log.debug(String.format("Getting label with name: %s", name));

        Label label = labelRepository.findByName(name);

        if(null == label)
            throw new ItemNotFoundException(name);

        log.debug(String.format("Found a label with name: %s", name));

        return label;
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public Label createLabel(Label label) {

        log.debug(String.format("Adding a new note with title: %s", label.getName()));

        Label persistedLabel = labelRepository.save(label);

        log.debug(String.format("Created a new label with id: %d and name: %s", label.getId(), persistedLabel.getName()));

        return persistedLabel;
    }

}
