package com.ipnetinstitute.artisans.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectNotFoundInDBException extends Exception{
    String objectName ;
    String objectId ;

}
