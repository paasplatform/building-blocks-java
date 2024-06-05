package org.paasplatform.data.meta;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    private String name;
    private String schema;
    private String catalog;
    private String remarks;
    private String type;
}
