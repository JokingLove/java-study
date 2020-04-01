package com.joking.springjpa.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 附件实体
 * @author yongqiang.guo
 * @create 2019-06-28
 */
@Document(collection = "medical_image")
public class MedicalImage {

    @Id
    private ObjectId id;

    /**
     * 统一用户id
     */
    @Field("user_id")
    private String userId;

    @Field("exam_info_id")
    private String examInfoId;

    @Field("dept_id")
    private String deptId;

    @Field("institute_id")
    private String instituteId;
    @Field("work_no")
    private String workNo;
    @Field("item_id")
    private Long itemId;

    /**
     * 1,需要打印在报告里面， 0 不需要打印在报告里面
     */
    @Field("to_report")
    private Integer toReport;

    @Field("fid")
    private String fid;
    /**
     * seaWeed 中返回的 code
     */
    @Field("code")
    private String code;
    /**
     * 文件的原始名称
     */
    @Field("file_name")
    private String fileName;
    /**
     * 文件的 uuid
     */
    @Field("uuid")
    private String uuid;
    /**
     * 文件类型 "image/jpeg"
     */
    @Field("file_type")
    private String fileType;
    /**
     * 文件的字节码长度
     */
    @Field("size")
    private Long length;

    @Field("position")
    private int position;
    /**
     * 后缀
     */
    @Field("suffix")
    private String suffix;

    /**
     * 文件查看（图片）或者下载接口
     */
    @Field("download_api")
    private String downloadApi;

    /**
     * 创建时间
     */
    @Field("create_time")
    private Date createTime;
    /**
     * 状态：1： 可用； 0：不可用
     */
    @Field("status")
    private Integer status;

    @Field("pacs_url")
    private String pacsUrl;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamInfoId() {
        return examInfoId;
    }

    public void setExamInfoId(String examInfoId) {
        this.examInfoId = examInfoId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getToReport() {
        return toReport;
    }

    public void setToReport(Integer toReport) {
        this.toReport = toReport;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDownloadApi() {
        return downloadApi;
    }

    public void setDownloadApi(String downloadApi) {
        this.downloadApi = downloadApi;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPacsUrl() {
        return pacsUrl;
    }

    public void setPacsUrl(String pacsUrl) {
        this.pacsUrl = pacsUrl;
    }

    @Override
    public String toString() {
        return "MedicalImage{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", examInfoId='" + examInfoId + '\'' +
                ", deptId='" + deptId + '\'' +
                ", instituteId='" + instituteId + '\'' +
                ", workNo='" + workNo + '\'' +
                ", itemId=" + itemId +
                ", toReport=" + toReport +
                ", fid='" + fid + '\'' +
                ", code='" + code + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", fileType='" + fileType + '\'' +
                ", length=" + length +
                ", position=" + position +
                ", suffix='" + suffix + '\'' +
                ", downloadApi='" + downloadApi + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", pacsUrl='" + pacsUrl + '\'' +
                '}';
    }
}
