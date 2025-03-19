package portercat.petstoreapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portercat.petstoreapi.models.Pet;
import portercat.petstoreapi.models.Tag;
import portercat.petstoreapi.repositories.ITagRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService
{
    private final ITagRepository _tagRepository;

    public List<Tag> getAllTags()
    {
        List<Tag> tags = new ArrayList<>();

        for(Tag tag : _tagRepository.findAll())
            tags.add(new Tag(tag.getId(),
                    tag.getName())
            );

        return tags;
    }
    public Tag createTag(Tag tag) { return _tagRepository.save(tag); }

    public List<Tag> getTagsByIds(List<Long> tagIds)
    {
        if (tagIds == null || tagIds.isEmpty())
            return Collections.emptyList();

        List<Tag> foundTags = new ArrayList<>();

        for(Tag tag : _tagRepository.findAllById(tagIds))
            foundTags.add(new Tag(tag.getId(),
                    tag.getName())
            );


        if (foundTags.size() != tagIds.size()) {
            Set<Long> foundIds = foundTags.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());

            Set<Long> missingIds = tagIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());
        }

        return foundTags;
    }
}
