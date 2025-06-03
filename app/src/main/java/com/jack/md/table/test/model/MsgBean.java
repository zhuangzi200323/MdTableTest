package com.jack.md.table.test.model;

import java.util.List;

public class MsgBean {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<ChoicesDTO> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChoicesDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoicesDTO> choices) {
        this.choices = choices;
    }

    public static class ChoicesDTO {
        private DeltaDTO delta;
        private Integer index;
        private Object finish_reason;

        public DeltaDTO getDelta() {
            return delta;
        }

        public void setDelta(DeltaDTO delta) {
            this.delta = delta;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Object getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(Object finish_reason) {
            this.finish_reason = finish_reason;
        }

        public static class DeltaDTO {
            private String role;
            private String content;

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
