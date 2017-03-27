package softgroup.ua.service.mapper;


import softgroup.ua.service.exception.ParsingException;

/**
 * @author Stanislav Rymar
 */

public interface GenericMapper<T1, T2> {

    T2 fromInternal(T1 internalObj) throws ParsingException;

    T1 toInternal(T2 externalObj) throws ParsingException;
}
