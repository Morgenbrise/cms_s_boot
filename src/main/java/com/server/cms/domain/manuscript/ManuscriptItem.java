package com.server.cms.domain.manuscript;

import com.server.cms.framework.common.BaseTimeEntity;
import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "YN_USE = 'Y'")
public class ManuscriptItem extends BaseTimeEntity {

    @Id
    @Column(name = "IND")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ind;

    @Column(name = "PATH")
    private String path;

    @Column(name = "NM_IMG")
    private String imageNm;

    @Column(name = "SIZE")
    private long size;

    @Column(name = "EXT")
    private String ext;

    @Column(name = "YN_USE")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType useYn;

    public ManuscriptItem(String path, String imageNm, long size, String ext) {
        this.path = path;
        this.imageNm = imageNm;
        this.size = size;
        this.ext = ext;
        this.useYn = YnType.Y;
    }
}
