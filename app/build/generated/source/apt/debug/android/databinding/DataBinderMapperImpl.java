package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.diogo.oliveira.mymovies.DataBinderMapperImpl());
  }
}
