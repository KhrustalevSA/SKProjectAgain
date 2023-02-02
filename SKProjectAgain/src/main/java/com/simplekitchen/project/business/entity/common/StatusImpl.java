package com.simplekitchen.project.business.entity.common;


import com.simplekitchen.project.business.entity.common.api.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс реализующий интерфейс Status
 * @see Status
 * @author KhrustalevSA
 * @since 09.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusImpl implements Status {

    /**
     * поле для показа статуса успеха операции
     */
    private boolean success;

    /**
     * поле описания статуса
     */
    private String description;
}
