
package com.binus.thesis.fisheryapp.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * @author febrihasan
 *
 * Class request sorting
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSorting {
  private String field;
  private Sort.Direction direction;
}
