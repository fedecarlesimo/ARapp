package it.unifi.micc.artguide;

public class ArtObj {
    private String nome;
    private String autore;
    private String descrizione;
    private String storia;
    private String luogo;
    private String anno_creazione;
    private String url_video;

    public ArtObj(String nome, String autore, String descrizione, String storia, String luogo, String anno_creazione, String url_video, String url_audio, String url_image) {
        this.nome = nome;
        this.autore = autore;
        this.descrizione = descrizione;
        this.storia = storia;
        this.luogo = luogo;
        this.anno_creazione = anno_creazione;
        this.url_video = url_video;
        this.url_audio = url_audio;
        this.url_image = url_image;
    }

    private String url_audio;
    private String url_image;

    public String getUrl_image() {
        return url_image;
    }

    public String getUrl_video() {
        return url_video;
    }

    public String getUrl_audio() {
        return url_audio;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public void setUrl_audio(String url_audio) {
        this.url_audio = url_audio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setStoria(String storia) {
        this.storia = storia;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setAnno_creazione(String anno_creazione) {
        this.anno_creazione = anno_creazione;
    }

    public String getNome() {
        return nome;
    }

    public String getAutore() {
        return autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getStoria() {
        return storia;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getAnno_creazione() {
        return anno_creazione;
    }


}
