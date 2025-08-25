package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer
{
    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialProfileRepository socialProfileRepository, SocialGroupRepository socialGroupRepository, SocialUserRepository socialUserRepository, PostRepository postRepository)
    {
        this.socialProfileRepository = socialProfileRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialUserRepository = socialUserRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData()
    {
        return args -> {

            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            socialUserRepository.save(user1);
            socialUserRepository.save(user2);
            socialUserRepository.save(user3);

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            group1.getUsers().add(user1);
            group1.getUsers().add(user2);

            group2.getUsers().add(user3);
            group2.getUsers().add(user1);

            socialGroupRepository.save(group1);
            socialGroupRepository.save(group2);

            user1.getGroups().add(group1);
            user1.getGroups().add(group2);

            user2.getGroups().add(group1);

            user3.getGroups().add(group2);

            socialUserRepository.save(user1);
            socialUserRepository.save(user2);
            socialUserRepository.save(user3);



            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);
        };
    }
}
