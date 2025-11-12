package space;

import payment.PaymentService;
import space.domain.Space;

public class SpaceService {
    private SpaceRepository spaceRepository= new SpaceRepository();
    private PaymentService paymentService=new PaymentService();

    public Long createSpace(Space space) {
        space.setPrice(paymentService.previewOnedayTotal(space));
        return spaceRepository.save(space);
    }
    public void deleteSpace(Long spaceId){
        if(spaceRepository.deleteById(spaceId)){
            System.out.println("삭제 완료");
        }else{
            System.out.println("삭제 실패");
        }
    }

    public void updateSpace(Long spaceId,Space updateSpace){
        if(spaceRepository.updateById(spaceId,updateSpace)){
            System.out.println("update 완료");
        }else{
            System.out.println("update 실패");
        }
    }
    public Space searchSpace(Long id) {
        return spaceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 장소가 없습니다.!"));
    }
    public void printSpaceList(){
        spaceRepository.findAll().forEach(System.out::println);
    }

}
