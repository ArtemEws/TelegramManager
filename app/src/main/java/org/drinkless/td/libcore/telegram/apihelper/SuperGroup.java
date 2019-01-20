package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.TdApi;

public class SuperGroup {
    TdApi.Supergroup supergroup;

    SuperGroup(TdApi.Supergroup supergroup) {
        this.supergroup = supergroup;
    }

    public long getId() {
        return supergroup.id;
    }

    public String getUsername() {
        return supergroup.username;
    }

    public boolean isChannel() {
        return supergroup.isChannel;
    }

    public boolean isCurrentUserAdmin() {
        if (supergroup.status.getConstructor() == TdApi.ChatMemberStatusAdministrator.CONSTRUCTOR ||
                supergroup.status.getConstructor() == TdApi.ChatMemberStatusCreator.CONSTRUCTOR) {
            return true;
        }
        return false;
    }

}

