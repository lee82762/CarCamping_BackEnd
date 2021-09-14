package com.Hanium.CarCamping.service.CampSite;

import com.Hanium.CarCamping.Exception.DuplicateCampSiteException;
import com.Hanium.CarCamping.Exception.NoSuchCampSiteException;
import com.Hanium.CarCamping.Exception.NoSuchMemberException;
import com.Hanium.CarCamping.Exception.NotCampSiteRegisterException;
import com.Hanium.CarCamping.domain.Region;
import com.Hanium.CarCamping.domain.dto.campsite.CreateCampSiteDto;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.CampSiteRepository;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.service.Point.PointService;
import com.Hanium.CarCamping.service.S3Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampsiteService {
    private final MemberRepository memberRepository;
    private final CampSiteRepository campSiteRepository;
    private final PointService pointService;
    private final RedisTemplate redisTemplate;
    private final S3Uploader s3Uploader;
    @Transactional
    public Long saveCampSite(CreateCampSiteDto createCampSiteDto, String email)   {
        Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchMemberException::new);
        Optional<CampSite> byName = campSiteRepository.findByName(createCampSiteDto.getName());
        if (byName.isPresent()) {
            if (byName.get().getRegion().toString().equals(createCampSiteDto.getRegion())) {
                throw new DuplicateCampSiteException("이미 등록되어있는 차박지입니다");
            }
        }
        CampSite save = campSiteRepository.save(CampSite.createCampSite(createCampSiteDto, member,getGeoDataByAddress(createCampSiteDto.getAddress())));
        pointService.create(member,"차박지 등록",100);
        redisTemplate.opsForZSet().add("ranking",member.getNickname(), member.getPoint());
        return save.getCampsite_id();
    }

    public CampSite findById(Long id) {
        return campSiteRepository.findById(id).orElseThrow(NoSuchCampSiteException::new);
    }
    public List<CampSite> findByRegion(Region region) {
        System.out.println(region.name());
        return campSiteRepository.findByRegion(region);
    }

    public CampSite findByName(String name) {
        return campSiteRepository.findByName(name).orElseThrow(NoSuchCampSiteException::new);
    }
    public List<CampSite> getAllCampSiteList() {
        return
                campSiteRepository.findAll();
    }
    @Transactional
    public void deleteCampSite(String email,Long campSite_id) {
        Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchMemberException::new);
        CampSite campSite = campSiteRepository.findById(campSite_id).orElseThrow(NoSuchCampSiteException::new);
        if (!campSite.getRegistrant().getId().equals(member.getId())) {
            throw new NotCampSiteRegisterException("차박지 등록자가 아닙니다");
        } else {
            pointService.create(member,"차박지 삭제",-100);
            redisTemplate.opsForZSet().add("ranking",member.getNickname(), member.getPoint());
            campSiteRepository.delete(campSite);
        }
    }
    public List<CampSite> getCampSiteByRegionAndDateASC(Region region) {
        return campSiteRepository.findByRegionOrderByCampsite_idAsc(region);
    }
    public List<CampSite> getCampSiteByRegionAndDateDESC(Region region) {
        return campSiteRepository.findByRegionOrderByCampsite_idDesc(region);
    }


    public List<CampSite> getCampSiteByScore() {
        return campSiteRepository.findAllByOrderByScoreDesc();
    }

    public List<CampSite> getCampSiteByRegionAndScoreDESC(Region region) {
        return campSiteRepository.findByRegionOrderByScoreDesc(region);
    }
    public List<CampSite> getCampSiteByRegionAndScoreASC(Region region) {
        return campSiteRepository.findByRegionOrderByScoreAsc(region);
    }
    public List<CampSite> getCampSiteByRegion(Region region) {
        return campSiteRepository.findByRegion(region);
    }


    public  String[] getGeoDataByAddress(String completeAddress) {
        try {
            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(completeAddress, "UTF-8")+"&key="+"AIzaSyAKdB0nlrs1NK8jpZAEKZ2aMk3qNSrDGjk"+"&language=ko";
            URL url = new URL(surl);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jo = new JSONObject(responseStrBuilder.toString());
            JSONArray results = jo.getJSONArray("results");
            if(results.length() > 0) {
                JSONObject jsonObject;
                jsonObject = results.getJSONObject(0);
                Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                System.out.println("LAT:\t\t"+lat);
                System.out.println("LNG:\t\t"+lng);
                String result[]=new String[2];
                result[0]=Double.toString(lat);
                result[1]=Double.toString(lng);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CampSite> getMyCampSite(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchMemberException::new);
        return campSiteRepository.findByRegistrant(member);
    }
    public List<CampSite> getCampSiteBySearchWord(String word) {

        return campSiteRepository.findByNameContainingOrderByScoreDesc(word);

    }
/*    public  Float[] findGeoPoint(String location) {

        if (location == null)
            return null;

        // setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
        // setLanguate : 인코딩 설정
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();

        try {
            Geocoder geocoder = new Geocoder();
            GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

            if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {

                GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
                LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

                Float[] coords = new Float[2];
                coords[0] = latitudeLongitude.getLat().floatValue();
                coords[1] = latitudeLongitude.getLng().floatValue();
                System.out.println(coords[0]);
                System.out.println(coords[1]);
                return coords;

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/

}
