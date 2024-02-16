package com.test.utility;

import com.test.constants.Constants;
import com.test.exception.config.ApiException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RangeUtil {

  private long start;

  private long end;

  public long len() {
    return (end - start) + 1;
  }

  public String lenStr() {
    return String.valueOf((end - start) + 1);
  }

  public static RangeUtil getRangeUtil(String range, long fileSize, boolean isImage) {
    // bytes=186253317-49549
    if (range == null) {
      return RangeUtil.getRangeUtil("bytes=0-" + Constants.PACE, fileSize, isImage);
    }
    String[] ranges = range.replace("bytes=", "").trim().split("-");
    long start = checkLong(ranges[0]);
    long end = 0;
    if (isImage) {
      return RangeUtil.builder().start(start).end(fileSize - 1).build();
    }
    if (ranges.length > 1) {
      end = checkLong(ranges[1]);
      if (end > fileSize) {
        end = fileSize - 1;
      }
      if ((end - start) > Constants.BAR) {
        throw new ApiException("Range passed the bar");
      }
      if ((end - start) < 0) {
        throw new ApiException("Range out of index");
      }
    } else {
      if ((start + Constants.PACE) > fileSize) {
        end = fileSize - 1;
      } else {
        end = start + Constants.PACE;
      }

    }
    if (fileSize < end) {
      end = fileSize - 1;
    }

    return RangeUtil.builder().start(start).end(end).build();
  }

  public static long checkLong(String s) {

    try {
      long l = Long.parseLong(s);
      if (l < 0) {
        throw new ApiException("Error Parsing a String to number expected a positive number");
      }
      return l;
    } catch (Exception e) {
      log.info("expected a positive number and we get this : {}", s);
      throw new ApiException("Error Parsing a String to number");
    }

  }

}
