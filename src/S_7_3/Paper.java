package S_7_3;

import java.io.Serializable;

public class Paper implements Serializable {
    private static final long serialVersionUID=1L;
    /**编程语言合集*/
    private String[] languages;
    /**掌握技术合集*/
    private String[] technics;
    /**困难部分合集*/
    private String[] parts;

    public Paper() {
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getTechnics() {
        return technics;
    }

    public void setTechnics(String[] technics) {
        this.technics = technics;
    }

    public String[] getParts() {
        return parts;
    }

    public void setParts(String[] parts) {
        this.parts = parts;
    }
}
