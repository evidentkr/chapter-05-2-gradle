package com.rubypaper.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardDsl is a Querydsl query type for BoardDsl
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardDsl extends EntityPathBase<BoardDsl> {

    private static final long serialVersionUID = -47056168L;

    public static final QBoardDsl boardDsl = new QBoardDsl("boardDsl");

    public final NumberPath<Long> cnt = createNumber("cnt", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QBoardDsl(String variable) {
        super(BoardDsl.class, forVariable(variable));
    }

    public QBoardDsl(Path<? extends BoardDsl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardDsl(PathMetadata metadata) {
        super(BoardDsl.class, metadata);
    }

}

