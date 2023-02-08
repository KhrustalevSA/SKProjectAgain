package com.simplekitchen.project.dao.entity.common.entity;

import com.simplekitchen.project.dao.entity.common.entity.api.LongList;
import lombok.*;

import java.util.List;

/**
 * Класс списка целых чисел
 * @author KhrustalevSA
 * @since 08.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LongListImpl implements LongList {
    private List<Long> longList;
}
