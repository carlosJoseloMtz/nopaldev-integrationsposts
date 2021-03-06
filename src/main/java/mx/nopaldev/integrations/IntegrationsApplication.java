package mx.nopaldev.integrations;

import mx.nopaldev.integrations.facades.BlogPostFacade;
import mx.nopaldev.integrations.services.impl.DefaultBlogPostService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegrationsApplication {
    private static final Logger LOG = Logger.getLogger(IntegrationsApplication.class.getName());

    public static void main(String[] args) {
        // configure the global logger for the application
        Logger.getGlobal().setLevel(Level.ALL);


        LOG.log(Level.INFO, "Initializing the application");

        // manually create the instance of the services
        final var blogPostService = new DefaultBlogPostService();
        final var blogPostFacade = new BlogPostFacade(blogPostService);

        // execute all custom logic
        try {
            blogPostFacade.pullPosts();
        } catch (final Throwable ex) {
            LOG.log(Level.SEVERE, "Error while processing the posts", ex);
        }
    }
}
