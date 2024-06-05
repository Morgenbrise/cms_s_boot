package com.server.cms.domain.thumbnail;

import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "YN_USE = 'Y'")
public class ThumbnailItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "NM_IMAGE")
    private String imageName;

    @Column(name = "PATH")
    private String path;

    @Column(name = "SIZE")
    private Long size;

    @Column(name = "EXT")
    private String ext;

    @Column(name = "YN_USE")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType useYn;

    public ThumbnailItem(String imageName, String path, Long size, String ext) {
        this.imageName = imageName;
        this.path = path;
        this.size = size;
        this.ext = ext;
        this.useYn = YnType.Y;
    }
}
