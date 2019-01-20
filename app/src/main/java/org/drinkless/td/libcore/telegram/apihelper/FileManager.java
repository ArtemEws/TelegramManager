package org.drinkless.td.libcore.telegram.apihelper;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class FileManager implements Client.ResultHandler {
    TClient host;

    public FileManager(TClient host) {
        this.host = host;
    }

    @Override
    public void onResult(TdApi.Object object) {
        TdApi.File file = (TdApi.File)object;
        File f = new File(file);

        host.frontHandler.handle("file", f);
    }

    public void downloadFile(FileManager.File file) {
        host.client.send(new TdApi.DownloadFile(file.getId(), 20), null);
    }

    public static class File {
        TdApi.File file;
        File (TdApi.File file) {
            this.file = file;
        }

        public String getLocalPath() {
            if (file.local == null) return null;
            return file.local.path;
        }

        public int getId() {
            return file.id;
        }
    }
}
