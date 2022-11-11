package com.example.demo.service.admin;

import com.example.demo.domain.ProductImg;
import com.example.demo.dto.admin.*;
import com.example.demo.exception.CustomInternalServerErrorException;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ResourceLoader resourceLoader;
    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<CategoryResponseDto>();
        productManagementRepository.getCategoryList().forEach(category -> {
            categoryResponseDtos.add(category.toDto());
        });
        return categoryResponseDtos;
    }
    @Override
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception {
        List<ProductMstOptionRespDto> list = new ArrayList<ProductMstOptionRespDto>();
        productManagementRepository.getProductMstList().forEach(pdtMst ->{
            list.add(pdtMst.toDto());
        });
        return list;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0){ //insert 안 되면
            throw new CustomInternalServerErrorException("상품 등록 실패"); //이 에러를 띄워라.
        }
    }

    @Override
    public List<?> getSizeList(int productId) throws Exception {
        List<ProductSizeOptionRespDto> list = new ArrayList<ProductSizeOptionRespDto>();

        productManagementRepository.getSizeList(productId).forEach(size ->{
            list.add(size.toDto());
        });
        return list;
    }

    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if (productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 오류");
        }
    }
    @Override
    public void checkDuplicatedSize(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.findProductSize(productRegisterDtlReqDto.toEntity()) > 0) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
            throw new CustomValidationException("Duplicated Error", errorMap);
        }
    }




    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
        if (productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않으셨습니다.");
            throw new CustomValidationException("ImgError", errorMap);
        }

        List<ProductImg> productImgs = new ArrayList<ProductImg>();

        productImgReqDto.getFiles().forEach(file -> {
            Resource resource = resourceLoader.getResource("classpath:static/upload/product");
            String targetFilePath = null;
            String srcFilePath = null;

            //프로덕트까지 못찾으면

            try{
                //해당경로에 이폴더가 존재하냐
                if(!resource.exists()) {
                    String targetTempPath = resourceLoader.getResource("classpath:static").getURI().toString();
                    String srcTempPath = resourceLoader.getResource("classpath:static").getURI().toString();
                    targetTempPath = targetTempPath.substring(targetTempPath.indexOf("/") + 1);
                    srcTempPath = srcTempPath.substring(srcTempPath.indexOf("/") + 1, srcTempPath.indexOf("target")) + "/src/main/resources/static";

                    System.out.println(targetTempPath);
                    System.out.println(srcTempPath);
                    File f = new File(targetTempPath + "/upload/product");
                    f.mkdirs();
                    f = new File(srcTempPath + "/upload/product");
                    f.mkdirs();
                }

                targetFilePath = resource.getURI().toString().substring(resource.getURI().toString().indexOf("/") + 1);
                srcFilePath = resource.getURI().toString().substring(resource.getURI().toString().indexOf("/") + 1, resource.getURI().toString().indexOf("target")) + "/src/main/resources/static/upload/product";
                System.out.println(targetFilePath);
                System.out.println(srcFilePath);

            } catch (IOException e){
                throw new RuntimeException(e);
            }

            String originName = file.getOriginalFilename();
            String extension = originName.substring(originName.lastIndexOf("."));
            String saveName = UUID.randomUUID().toString().replace("-","") + extension;
            Path targetPath = Paths.get(targetFilePath + "/" + saveName);
            Path srcPath = Paths.get(srcFilePath + "/" + saveName);


            try {
                Files.write(targetPath, file.getBytes());
                Files.write(srcPath, file.getBytes());

            } catch (IOException e) {
                throw new CustomInternalServerErrorException(e.getMessage());
            }
            productImgs.add(ProductImg.builder()
                    .pdt_id(productImgReqDto.getPdtId())
                    .origin_name(originName)
                    .save_name(saveName)
                    .build());
            });
        productManagementRepository.saveProductImg(productImgs);
    }

}