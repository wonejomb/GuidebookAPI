package de.wonejo.wuidebook.api.wcontent;

import java.util.List;

public interface WContentProcessor {

    <T> WContentTag<T> buildTag (List<String> pTag);

}
