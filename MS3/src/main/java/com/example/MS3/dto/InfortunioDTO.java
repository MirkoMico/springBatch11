package com.example.MS3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfortunioDTO {

    @JsonProperty("DatiConCadenzaSemestraleInfortuni")
    private List<Infortunio> datiConCadenzaSemestraleInfortuni;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Infortunio {
        @JsonProperty("DataRilevazione")
        private String dataRilevazione;
        @JsonProperty("LuogoNascita")
        private String luogoNascita;
        @JsonProperty("Regione")
        private String regione;
        @JsonProperty("Genere")
        private String genere;
        @JsonProperty("Gestione")
        private String gestione;
        @JsonProperty("IdentificativoCaso")
        private String identificativoCaso;
        @JsonProperty("DataProtocollo")
        private String dataProtocollo;
        @JsonProperty("DataAccadimento")
        private String dataAccadimento;
        @JsonProperty("DataDefinizione")
        private String dataDefinizione;
        @JsonProperty("DataMorte")
        private String dataMorte;
        @JsonProperty("LuogoAccadimento")
        private String luogoAccadimento;
        @JsonProperty("IdentificativoInfortunato")
        private String identificativoInfortunato;
        @JsonProperty("Eta")
        private String eta;
        @JsonProperty("ModalitaAccadimento")
        private String modalitaAccadimento;
        @JsonProperty("ConSenzaMezzoTrasporto")
        private String conSenzaMezzoTrasporto;
        @JsonProperty("DefinizioneAmministrativa")
        private String definizioneAmministrativa;
        @JsonProperty("DefinizioneAmministrativaEsitoMortale")
        private String definizioneAmministrativaEsitoMortale;
        @JsonProperty("Indennizzo")
        private String indennizzo;
        @JsonProperty("DecisioneIstruttoriaEsitoMortale")
        private String decisioneIstruttoriaEsitoMortale;
        @JsonProperty("GradoMenomazione")
        private String gradoMenomazione;
        @JsonProperty("GiorniIndennizzati")
        private String giorniIndennizzati;
        @JsonProperty("IdentificativoDatoreLavoro")
        private String identificativoDatoreLavoro;
        @JsonProperty("PosizioneAssicurativaTerritoriale")
        private String posizioneAssicurativaTerritoriale;
        @JsonProperty("SettoreAttivitaEconomica")
        private String settoreAttivitaEconomica;
        @JsonProperty("GestioneTariffaria")
        private String gestioneTariffaria;
        @JsonProperty("GrandeGruppoTariffario")
        private String grandeGruppoTariffario;
    }
}
