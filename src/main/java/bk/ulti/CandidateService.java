package bk.ulti;


/**
 * Created by quangminh on 18/09/2017.
 */
public interface CandidateService {
    void saveCandidateFromPartnerResult(long id,int year1,int year2);
    double CNMeasure(long id,int year1,int year2);
    double AAMeasure(long id,int year1,int year2);
    double JCMeasure(long id,int year1,int year2);
    double WCNMeasure(long id,int year1,int year2);
    double WAAMeasure(long id,int year1,int year2);
    double WJCMeasure(long id,int year1,int year2);
}
