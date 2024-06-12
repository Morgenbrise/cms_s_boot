package com.server.cms.domain.support;

import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_NOTI")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "NOTI_SEQ_GENERATOR", sequenceName = "NOTI_SEQ", initialValue = 1, allocationSize = 2)
@Getter
public class Notification {

    @Id
    @Column(name = "IND")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ind;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SHOW_TARGET")
    private String target;

    @Column(name = "YN_SHOW")
    private YnType showYn;

    @Column(name = "YN_FIX")
    private YnType fixYn;

}
