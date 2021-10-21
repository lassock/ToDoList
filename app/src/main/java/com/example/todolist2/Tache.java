package com.example.todolist2;

public class Tache {
    private String id;
    private String non_tache;
    boolean fini;

    public Tache( String non_tache, boolean fini) {

        this.non_tache = non_tache;
        this.fini = fini;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNon_tache() {
        return non_tache;
    }

    public void setNon_tache(String non_tache) {
        this.non_tache = non_tache;
    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id='" + id + '\'' +
                ", non_tache='" + non_tache + '\'' +
                ", fini=" + fini +
                '}';
    }
}
