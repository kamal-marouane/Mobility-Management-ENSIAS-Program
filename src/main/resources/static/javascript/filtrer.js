const showInputButtonFilier = document.getElementById('show-input-filier');
const inputListContainer = document.getElementById('input-container-filier');
showInputButtonFilier.addEventListener('click',function() {
    inputListContainer.style.display = inputListContainer.style.display === 'none' ? 'block' : 'none';
})


const showInputButtonS3 = document.getElementById('show-input-S3');
const inputContainerS3 = document.getElementById('input-container-S3');
showInputButtonS3.addEventListener('click', function() {
    inputContainerS3.style.display = inputContainerS3.style.display === 'none' ? 'block' : 'none';
});



const showInputButtonSeuil1A = document.getElementById('show-input-seuil1A');
const inputContainerSeuil1A = document.getElementById('input-container-seuil1A');
showInputButtonSeuil1A.addEventListener('click', function() {
    inputContainerSeuil1A.style.display = inputContainerSeuil1A.style.display === 'none' ? 'block' : 'none';
});


