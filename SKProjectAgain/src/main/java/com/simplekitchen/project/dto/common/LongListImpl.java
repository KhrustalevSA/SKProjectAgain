package com.simplekitchen.project.dto.common;

import com.simplekitchen.project.dto.common.api.LongList;
import lombok.*;

import java.util.List;

/**
 * Класс списка целых чисел
 * @author KhrustalevSA
 * @see com.simplekitchen.project.dto.common.api.LongList
 * @since 08.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LongListImpl implements LongList {

    /**
     * список целых чисел
     */
    private List<Long> longList;
}
