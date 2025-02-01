package hudson.plugins.favorite.user;

import hudson.Extension;
import hudson.model.Item;
import hudson.model.RootAction;
import hudson.model.User;
import jenkins.model.Jenkins;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * TODO
 */
@Extension
public class FavoriteRootAction implements RootAction {

    @Override
    public String getIconFileName() {
        if (User.current() == null) {
            return null;
        }

        return "symbol-star-outline plugin-ionicons-api";
    }

    @Override
    public String getDisplayName() {
        return "Favorites";
    }

    @Override
    public String getUrlName() {
        if (User.current() == null) {
            return null;
        }

        return User.current().getUrl() + "/favorites";
    }

    public Set<String> getFavorites() {
        if (User.current() == null) {
            return null;
        }

        return User.current().getProperty(FavoriteUserProperty.class).getAllFavorites();
    }

    // TODO - Move
    public String toItemUrl(String fullName) {
        if (StringUtils.isEmpty(fullName)) {
            return null;
        }

        Jenkins jenkins = Jenkins.get();
        Item item = jenkins.getItemByFullName(fullName);
        if (item == null) {
            return null;
        }

        return jenkins.getRootUrl() + item.getUrl();
    }
}
