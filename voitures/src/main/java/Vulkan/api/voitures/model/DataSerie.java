package Vulkan.api.voitures.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        @JsonAlias("Title")
        String nome,
        @JsonAlias("Year")
        String year,
        @JsonAlias("Released")
        String released,
        @JsonAlias("Runtime")
        String Runtime,
        @JsonAlias("Awards")
        String awards,
        @JsonAlias("totalSeasons")
        Integer totalTemporadas

) {
}
